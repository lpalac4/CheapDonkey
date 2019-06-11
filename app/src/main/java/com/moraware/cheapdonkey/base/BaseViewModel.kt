package com.moraware.cheapdonkey.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.Bindable
import android.databinding.Observable
import android.databinding.PropertyChangeRegistry
import com.moraware.cheapdonkey.BR
import com.moraware.cheapdonkey.CheapDonkeyApplication
import com.moraware.cheapdonkey.logger.CheapDonkeyLogger
import com.moraware.cheapdonkey.util.SingleLiveEvent
import com.moraware.domain.client.base.IUseCaseClient
import javax.inject.Inject

abstract class BaseViewModel : ViewModel(), Observable {

    @Inject
    lateinit var mUseCaseClient: IUseCaseClient
    @Inject
    lateinit var mLogger: CheapDonkeyLogger

    init {
        CheapDonkeyApplication.getInstance().getApplicationComponent().inject(this)
    }

    @Transient private val mCallbacks: PropertyChangeRegistry = PropertyChangeRegistry()

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        mCallbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        mCallbacks.remove(callback)
    }

    /**
     * Notifies listeners that all properties of this instance have changed.
     */
    fun notifyChange() {
        mCallbacks.notifyCallbacks(this, 0, null)
    }

    /**
     * Notifies listeners that a specific property has changed. The getter for the property
     * that changes should be marked with [Bindable] to generate a field in
     * `BR` to be used as `fieldId`.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    fun notifyPropertyChanged(fieldId: Int) {
        mCallbacks.notifyCallbacks(this, fieldId, null)
    }

    private var mIsProcessing: Boolean = false

    @Bindable
    fun isProcessing() : Boolean {
        return mIsProcessing
    }

    fun setProcessing(value : Boolean) {
        if(mIsProcessing != value)
        {
            mIsProcessing = value
            notifyPropertyChanged(BR.processing)
        }
    }

    private var mHasError: Boolean = false
    private var mErrorMessage: SingleLiveEvent<String> = SingleLiveEvent()

    @Bindable
    fun getHasError() : Boolean {
        return mHasError
    }

    fun setHasError(value : Boolean) {
        if(mHasError != value)
        {
            mHasError = value
            notifyPropertyChanged(BR.hasError)
        }
    }

    fun getErrorMessage() : MutableLiveData<String> {
        return mErrorMessage
    }

    fun setErrorMessage(value : String) {
        mErrorMessage.value = value
    }

    abstract fun loadData()
}