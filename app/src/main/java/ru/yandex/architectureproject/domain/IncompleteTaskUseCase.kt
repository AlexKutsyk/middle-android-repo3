package ru.yandex.architectureproject.domain

import kotlinx.coroutines.Job
import ru.yandex.architectureproject.data.repository.TaskRepository

class IncompleteTaskUseCase(
    private val repository: TaskRepository,
) {
    suspend operator fun invoke(taskId: Int, jobMap: MutableMap<Int, Job>) {
        repository.incompleteTask(taskId)
        jobMap[taskId]?.cancel()
    }
}