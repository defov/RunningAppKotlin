package com.example.myrun.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myrun.db.Run
import com.example.myrun.other.SortType
import com.example.myrun.repositories.MainRepository
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    val mainRepository: MainRepository
): ViewModel() {

    private val runsSortedByDate = mainRepository.getAllRunsSortedByDate()
    private val runsSortedByDistance = mainRepository.getAllRunsSortedByDistance()
    private val runsSortedByAvgSpeed = mainRepository.getAllRunsSortedByAvgSpeed()
    private val runsSortedByDuration = mainRepository.getAllRunsSortedByDuration()
    private val runsSortedByCaloriesBurned = mainRepository.getAllRunsSortedByCaloriesBurned()

    val runs = MediatorLiveData<List<Run>>()
    var sortType = SortType.DATE
    init {
        runs.addSource(runsSortedByDate) { result ->
            if(sortType == SortType.DATE) {
                result?.let { runs.value = it }
            }
        }
        runs.addSource(runsSortedByDistance) { result ->
            if(sortType == SortType.DISTANCE) {
                result?.let { runs.value = it }
            }
        }
        runs.addSource(runsSortedByAvgSpeed) { result ->
            if(sortType == SortType.AVG_SPEED) {
                result?.let { runs.value = it }
            }
        }
        runs.addSource(runsSortedByDuration) { result ->
            if(sortType == SortType.DURATION) {
                result?.let { runs.value = it }
            }
        }
        runs.addSource(runsSortedByCaloriesBurned) { result ->
            if(sortType == SortType.CALORIES_BURNED) {
                result?.let { runs.value = it }
            }
        }
    }

    fun sortRuns(sortType: SortType) = when(sortType) {
        SortType.DATE -> runsSortedByDate.value?.let { runs.value = it }
        SortType.DISTANCE -> runsSortedByDistance.value?.let { runs.value = it }
        SortType.AVG_SPEED -> runsSortedByAvgSpeed.value?.let { runs.value = it }
        SortType.DURATION -> runsSortedByDuration.value?.let { runs.value = it }
        SortType.CALORIES_BURNED -> runsSortedByCaloriesBurned.value?.let { runs.value = it }
    }.also {
        this.sortType = sortType
    }

    fun insertRun(run: Run) = viewModelScope.launch {
        mainRepository.runDao.insertRun(run)
    }
}