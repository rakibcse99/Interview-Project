package com.rakibcse99.gozayaanui.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rakibcse99.gozayaanui.base.ErrorResponse
import com.rakibcse99.gozayaanui.base.Resource
import com.rakibcse99.gozayaanui.models.RecommendedModel
import com.rakibcse99.gozayaanui.repository.RecommendedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendedViewModel @Inject constructor(val repository: RecommendedRepository) :
    ViewModel() {

    private var _recommendedModelResult =
        MutableSharedFlow<Resource<MutableList<RecommendedModel>, ErrorResponse>>()
    var recommendedModelResult: SharedFlow<Resource<MutableList<RecommendedModel>, ErrorResponse>> =
        _recommendedModelResult

    fun getRecommendedList() = viewModelScope.launch {
        _recommendedModelResult.emit(Resource.loading(null))
        _recommendedModelResult.emit(repository.getRecommendedList())
    }

}


