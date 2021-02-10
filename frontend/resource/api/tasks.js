import Vue from 'vue'

const tasks = Vue.resource('/tasks{/id}')

export default {
    add: task => tasks.save({}, task),
    getAll: () => {
                return tasks.get({})
            },
    get: id => tasks.get({id}),
    update: task => tasks.update({id : task.id}, task),
    remove: task => tasks.remove({id: task.id}),
    getSentTasks: () => {
        return tasks.get({id: 'outgoing/completed'})
    },
    getNew: () => {
        return tasks.get({id: 'new'})
    },
    getPerformed: () => {
        return tasks.get({id: 'performed'})
    },
    getCompleted: () => {
        return tasks.get({id: 'completed'})
    }
}