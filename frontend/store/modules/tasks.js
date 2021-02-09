import tasksApi from "../../resource/api/tasks";
import filesAPI from "../../resource/api/files";

export default {

    state:{
        tasks: [],
        sentTasks: []
    },

    getters: {
        getTasks: state => state.tasks,
        getNewTasks: state => state.tasks.filter( i => i.status === 'NEW'),
        getPerformedTasks: state => state.tasks.filter( i => i.status === 'PERFORMED'),
        getCompletedTasks: state => state.tasks.filter( i => i.status === 'COMPLETED'),
        getSentTasks: state => state.sentTasks
    },

    mutations:{

        getAllTasksMutation(state, tasks){
            state.tasks = tasks;
        },

        getSentTasksMutation(state, sentTasks){
            state.sentTasks = sentTasks;
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

        async getSentTasksAction({commit}){
            const result = await tasksApi.getSentTasks();
            const data = await result.json();
            if (result.ok){
                commit('getSentTasksMutation', data)
            }
        },

        async addTaskAction({commit},task){
            for (let i = 0; i < task.files.length; i++) {
                const formData = new FormData();
                formData.append('file', task.files[i]);
                const result1 = await filesAPI.add(formData);
                task.files[i] = await result1.json();
            }
            const result = await tasksApi.add(task);
        },

        async updateTaskAction({commit}, body){
             if (body.files !== undefined) {
                 for (let i = 0; i < body.files.length; i++) {
                     const formData = new FormData();
                     formData.append('file', body.files[i]);
                     const result1 = await filesAPI.add(formData);
                     const file = await result1.json();
                     body.task.documents.push(file);
                 }
             }
            const result = await tasksApi.update(body.task);
            const data = await result.json();
            if (result.ok) {
                commit('updateTaskMutation', data)
            }
        },

        async deleteTaskAction({commit}, task){
            const result = await tasksApi.delete(task);
            if (result.ok) {
                commit('deleteTaskMutation', task)
            }
        }
    }
}

