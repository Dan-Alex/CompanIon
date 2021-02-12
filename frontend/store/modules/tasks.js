import tasksApi from "../../resource/api/tasks";
import filesAPI from "../../resource/api/files";

export default {

    state:{
        newTasks: [],
        performedTasks: [],
        completedTasks: [] ,
        sentTasks: []
    },

    getters: {
        getNewTasks: state => state.newTasks,
        getPerformedTasks: state => state.performedTasks,
        getCompletedTasks: state => state.completedTasks,
        getSentTasks: state => state.sentTasks
    },

    mutations:{

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

        updatePerformedTaskMutation(state, task){
            const updIndex = state.performedTasks.findIndex(i => i.id === task.id);
            state.performedTasks = [
                ...state.performedTasks.slice(0, updIndex),
                task,
                ...state.performedTasks.slice(updIndex+1)
            ]
        },

        updateCompletedTaskMutation(state, task){
            const updIndex = state.completedTasks.findIndex(i => i.id === task.id);
            state.completedTasks = [
                ...state.completedTasks.slice(0, updIndex),
                task,
                ...state.completedTasks.slice(updIndex+1)
            ]
        },

        deleteNewTaskMutation(state, task){
            const delIndex = state.newTasks.findIndex(i => i.id === task.id);
            state.newTasks = [
                ...state.newTasks.slice(0, delIndex),
                ...state.newTasks.slice(delIndex+1)
            ]
        },

        deletePerformedTaskMutation(state, task){
            const delIndex = state.performedTasks.findIndex(i => i.id === task.id);
            state.performedTasks = [
                ...state.performedTasks.slice(0, delIndex),
                ...state.performedTasks.slice(delIndex+1)
            ]
        }
    },

    actions: {

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
                switch(data.status){
                    case 'PERFORMED': {
                        commit('deleteNewTaskMutation', data);
                        commit('updatePerformedTaskMutation', data);
                        break;
                    }
                    case 'COMPLETED': {
                        commit('deletePerformedTaskMutation', data);
                        commit('updateCompletedTaskMutation', data);
                        break;
                    }
                }
            }
        }
    }
}

