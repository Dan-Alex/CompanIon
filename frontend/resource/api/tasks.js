import Vue from 'vue'

const tasks= Vue.resource('/tasks{/id}')
const sentTasks = Vue.resource('/tasks/sent')

export default {
    add: task => tasks.save({}, task),
    getAll: () => {
                return tasks.get({})
            },
    get: id => tasks.get({id}),
    update: task => tasks.update({id : task.id}, task),
    remove: task => tasks.remove({id: task.id}),
    getSentTasks: () => {
        return sentTasks.get({})
    },
}