package com.teamtuna.emotionaldiary.compose.arch_compose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.teamtuna.emotionaldiary.usecase.MainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class ArchComposeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val useCase: MainUseCase,
) : ViewModel() {

    // LiveData.observeAsState()는 androidx.compose.runtime:runtime-livedata:$composeVersion 아티팩트에 포함됩니다.
    private val _test = MutableLiveData<String>()
    fun getTestLiveData(): LiveData<String> {
        useCase().onSuccess {
            _test.value = it
        }.onFailure {
            _test.value = it.message
        }
        return _test
    }

    // Flow.collectAsState()는 추가 종속 항목이 필요하지 않습니다.
    fun getTestFlow() = flow {
        emit("emit tuna")
    }
}
