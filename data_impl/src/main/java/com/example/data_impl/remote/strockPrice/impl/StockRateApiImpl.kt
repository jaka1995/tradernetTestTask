package com.example.data_impl.remote.strockPrice.impl

import com.example.data_impl.remote.strockPrice.StockRateApi
import com.example.data_impl.remote.strockPrice.dto.StocksPriceDto
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import com.google.gson.Gson
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.processors.PublishProcessor
import java.net.URISyntaxException

class StockRateApiImpl(
    private val gson: Gson
) : StockRateApi {

    companion object {
        private const val WS_URL = "https://ws3.tradernet.ru/"
        private const val EMIT_RATES = "sup_updateSecurities2"
        private const val EVENT = "connect"
        private val STOCK_LIST = listOf(
            "RSTI",
            "GAZP",
            "MRKZ",
            "RUAL",
            "HYDR",
            "MRKS",
            "SBER",
            "FEES",
            "TGKA",
            "VTBR",
            "ANH.US",
            "VICL.US",
            "BURG. US",
            "NBL.US",
            "YETI.US",
            "WSFS.US",
            "NIO.US",
            "DXC.US",
            "MIC.US",
            "HSBC.US",
            "EXPN.EU",
            "GSK.EU",
            "SH P.EU",
            "MAN.EU",
            "DB1.EU",
            "MUV2.EU",
            "TATE.EU",
            "KGF.EU",
            "MGGT.EU",
            "SGGD.EU"
        )
    }

    private val mSocket: Socket? by lazy {
        try {
            IO.socket(WS_URL)
        } catch (e: URISyntaxException) {
            null
        }
    }
    private val messagesFromSocketEmitter: PublishProcessor<StocksPriceDto> =
        PublishProcessor.create()
    private val socketMessagesListener = Emitter.Listener { any ->
        messagesFromSocketEmitter.onNext(
            gson.fromJson<StocksPriceDto>(any[0].toString(), StocksPriceDto::class.java)
        )
        mSocket?.emit(EMIT_RATES, STOCK_LIST)
    }

    override fun connect(): Completable {
        return Completable.fromAction {
            mSocket?.connect()
            mSocket?.on(EVENT, socketMessagesListener)
            return@fromAction
        }
    }

    override fun disconnect(): Completable {
        return Completable.fromAction {
            mSocket?.disconnect()
            mSocket?.off()
            return@fromAction
        }
    }

    override fun observeStockPrice(): Flowable<StocksPriceDto> {
        return messagesFromSocketEmitter
    }

}