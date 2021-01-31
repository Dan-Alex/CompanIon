import tasksApi from "../api/tasks";

export default {

    state:{
        tasks: [],
    },

    getters: {
        getTasks: state => state.tasks,
        getNewTasks: state => state.tasks.filter( i => i.status === 'NEW'),
        getPerformedTasks: state => state.tasks.filter( i => i.status === 'PERFORMED'),
        getCompletedTasks: state => state.tasks.filter( i => i.status === 'COMPLETED')
    },

    mutations:{

        getAllTasksMutation(state, tasks){
            state.tasks = tasks;
        },

        addTaskMutation(state, task){
            state.tasks = [
                ...state.tasks,
                task
            ]
        },

        updateTaskMutation(state, task){
            const updIndex = state.tasks.findIndex(i => i.id === task.id);
            state.tasks = [
                ...state.tasks.slice(0, updIndex),
                task,
                ...state.tasks.slice(updIndex+1)
            ]
        },

        deleteTaskMutation(state, task){
            const delIndex = state.tasks.findIndex(i => i.id === task.id);
            state.tasks = [
                ...state.tasks.slice(0, delIndex),
                ...state.tasks.slice(delIndex+1)
            ]
        }
    },

    actions: {

        async getAllTasksAction({commit}){
            const result = await tasksApi.getAll();
            const data = await result.json();
            if (result.ok){
                commit('getAllTasksMutation', data)
            }
        },

        async addTaskAction({commit}, task){
            const result = await tasksApi.add(task);
            const data = await result.json();
            if (result.ok) {
                commit('addTaskMutation', data)
            }
        },

        async updateTaskAction({commit}, task){
            const result = await tasksApi.update(task);
            const data = await result.json();
            commit('updateTaskMutation', data)
        },

        async deleteTaskAction({commit}, task){
            const result = await tasksApi.delete(task);
            if (result.ok) {
                commit('deleteTaskMutation', task)
            }
        }
    }
}

