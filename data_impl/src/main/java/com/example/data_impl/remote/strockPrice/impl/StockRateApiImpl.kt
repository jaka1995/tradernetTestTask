package com.example.data_impl.remote.strockPrice.impl

import com.example.data_impl.remote.strockPrice.StockRateApi
import com.example.data_impl.remote.strockPrice.dto.StockPriceDto
import com.example.data_impl.remote.strockPrice.dto.StocksPriceDto
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.processors.PublishProcessor
import org.json.JSONArray
import org.json.JSONObject
import java.lang.reflect.Type
import java.net.URISyntaxException


class StockRateApiImpl(
    private val gson: Gson
) : StockRateApi {

    companion object {
        private const val WS_URL = "https://ws3.tradernet.ru/"
        private const val EMIT_RATES = "sup_updateSecurities2"
        private const val CONNECT_EVENT = "connect"
        private const val Q_EVENT = "q"
        private const val RSTI = "RSTI"
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

    private val socketConnectionListener = Emitter.Listener { any ->
        val temp =  JSONArray()
        STOCK_LIST.forEach {
            temp.put(it)
        }
        mSocket?.emit(EMIT_RATES, temp)
    }

    private val socketStockListener = Emitter.Listener { any ->
        gson.fromJson<StocksPriceDto>(any.last().toString(), StocksPriceDto::class.java)?.let {
            messagesFromSocketEmitter.onNext(it)
        }
    }

    override fun connect(): Completable {
        return Completable.fromAction {
            mSocket?.connect()
            mSocket?.on(Q_EVENT, socketStockListener)
            mSocket?.on(CONNECT_EVENT, socketConnectionListener)
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