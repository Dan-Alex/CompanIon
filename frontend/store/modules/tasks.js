import tasksApi from "../../resource/api/tasks";
import filesAPI from "../../resource/api/files";

export default {

    state:{
        tasks: [],
        newTasks: [],
        performedTasks: [],
        completedTasks: [] ,
        sentTasks: []
    },

    getters: {
        getTasks: state => state.tasks,
        getNewTasks: state => state.newTasks,
        getPerformedTasks: state => state.performedTasks,
        getCompletedTasks: state => state.completedTasks,
        getSentTasks: state => state.sentTasks
    },

    mutations:{

        getAllTasksMutation(state, tasks){
            state.tasks = tasks;
        },

        getNewTasksMutation(state, newTasks){
          state.newTasks = newTasks;
        },

        getPerformedTasksMutation(state, performedTasks){
            state.performedTasks = performedTasks;
        },

        getCompletedTasksMutation(state, completedTasks){
            state.completedTasks = completedTasks;
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

        async getNewTasksAction({commit}){
            const result = await tasksApi.getNew();
            const data = await result.json();
            if (result.ok){
                commit('getNewTasksMutation', data)
            }
        },

        async getPerformedTasksAction({commit}){
            const result = await tasksApi.getPerformed();
            const data = await result.json();
            if (result.ok){
                commit('getPerformedTasksMutation', data)
            }
        },

        async getCompletedTasksAction({commit}){
            const result = await tasksApi.getCompleted();
            const data = await result.json();
            if (result.ok){
                commit('getCompletedTasksMutation', data)
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
            if (task.documents !== undefined) {
                for (let i = 0; i < task.documents.length; i++) {
                    const formData = new FormData();
                    formData.append('file', task.documents[i]);
                    const result1 = await filesAPI.add(formData);
                    task.documents[i] = await result1.json();
                }
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

