package ru.yandex.architectureproject.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.yandex.architectureproject.data.repository.TaskRepository

class CompleteTaskUseCase(
    private val repository: TaskRepository,
) {
    suspend fun invoke(taskId: Int, jobMap: MutableMap<Int, Job>) {
        repository.completeTask(taskId)
        val scope = jobMap[taskId]?.let { CoroutineScope(it) }
        scope?.launch {
            delay(10_000L)
            repository.deleteTask(taskId)
        }
    }
}
