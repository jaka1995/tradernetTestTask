package com.example.feature_stock_price.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain_api.StockInteractor
import com.example.feature_stock_price.base.ThreadExecutors
import com.example.feature_stock_price.di.StockComponentInjector
import com.example.feature_stock_price.ui.StockDvo
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

@Suppress("UNCHECKED_CAST")
class StockViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StockViewModel(
            interactor = StockComponentInjector.interactor(),
            threadExecutors = StockComponentInjector.threadExecutors()
        ) as T
    }

}

class StockViewModel(
    private val interactor: StockInteractor,
    private val threadExecutors: ThreadExecutors
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val stocksDvo = MutableLiveData<List<StockDvo>>()

    init {
        interactor
            .connect()
            .subscribeOn(threadExecutors.apiThreadExecutor.scheduler)
            .observeOn(threadExecutors.uiThreadExecutor.scheduler)
            .subscribe()
            .bind()

        interactor
            .observeStockPrice()
            .subscribeOn(threadExecutors.apiThreadExecutor.scheduler)
            .observeOn(threadExecutors.uiThreadExecutor.scheduler)
            .doOnNext {
                stocksDvo.value = it.map {
                    StockDvo(
                        title = it.tickerIdentifier,
                        subTitle = it.tickerName,
                        imageUrl = it.tickerImage,
                        percent = it.tickerDifferenceInPercent.toString(),
                        difference = "${it.tickerLastPrice} (${it.tickerPriceDifference})",
                        color = if (it.tickerPriceDifference >= 0) StockDvo.COLORED.GREEN else StockDvo.COLORED.RED
                    )
                }
            }
            .subscribe()
            .bind()
    }

    fun observeStocks(): MutableLiveData<List<StockDvo>> {
        return stocksDvo
    }

    private fun Disposable.bind() = compositeDisposable.add(this)

    override fun onCleared() {
        interactor
            .disconnect()
            .subscribeOn(threadExecutors.apiThreadExecutor.scheduler)
            .observeOn(threadExecutors.uiThreadExecutor.scheduler)
            .subscribe()
            .bind()


        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
        super.onCleared()
    }
}