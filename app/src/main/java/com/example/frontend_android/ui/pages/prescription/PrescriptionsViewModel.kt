package com.example.frontend_android.ui.pages.prescription

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frontend_android.data.model.dao.AlarmDao
import com.example.frontend_android.data.model.dao.PrescriptionDao
import com.example.frontend_android.data.model.entities.AlarmRecord
import com.example.frontend_android.data.model.relations.PrescriptionWithRelations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalTime
import javax.inject.Inject
import kotlin.math.abs

data class PrescriptionsState (
    val prescriptionsWithRelations: List<PrescriptionWithRelations> = emptyList(),
    val alarms : List<AlarmRecord> = emptyList(),
    val closestAlarm: String = "",
)

@HiltViewModel
class PrescriptionsViewModel  @Inject constructor (
    private val prescriptionDao: PrescriptionDao,
    private val alarmDao: AlarmDao
): ViewModel() {

    private val _state = mutableStateOf(PrescriptionsState())
    val state: State<PrescriptionsState> = _state

    private var getPrescriptionsJob: Job? = null
    private var getAlarmsJob: Job? = null

    init {
        retrievePrescriptions()
        retrieveAlarms()
    }

    private fun retrievePrescriptions() {
        getPrescriptionsJob?.cancel()
        getPrescriptionsJob = prescriptionDao.getPrescriptionsWithRelations()
            .onEach { prescriptionsWithRelations ->
                _state.value = state.value.copy(
                    prescriptionsWithRelations = prescriptionsWithRelations.sortedBy { it.prescription.formatDate }.reversed()
                    ,
                )
            }
            .launchIn(viewModelScope)
    }

    private fun retrieveAlarms() {
        getAlarmsJob?.cancel()
        getAlarmsJob = alarmDao.getAllAlarms()
            .onEach { alarms ->
                _state.value = state.value.copy(
                    alarms = alarms
                )

                getClosestAlarm(alarms)
            }
            .launchIn(viewModelScope)
    }

    private fun getClosestAlarm(alarms : List<AlarmRecord>) {
        if (alarms.isEmpty()) {
            return
        }

        val closestAlarm = alarms.filter { it.isScheduled }.minByOrNull { alarm ->
            val alarmTime = LocalTime.of(alarm.hours, alarm.minutes)
            val diff = abs(LocalTime.now().toSecondOfDay() - alarmTime.toSecondOfDay())
            diff
        } ?: return

        _state.value = state.value.copy(
            closestAlarm = String.format("%02dh%02d", closestAlarm!!.hours, closestAlarm.minutes)
        )
    }
}