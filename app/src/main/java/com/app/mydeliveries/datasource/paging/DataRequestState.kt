package com.app.mydeliveries.datasource.paging

class DataRequestState(val status: Status) {
    companion object {
        val LOADED: DataRequestState = DataRequestState(Status.SUCCESS)
        val LOADING: DataRequestState = DataRequestState(Status.RUNNING)

    }
    enum class Status {
        RUNNING,
        SUCCESS,
        FAILED
    }

}