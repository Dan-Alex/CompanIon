import Vue from 'vue'

const tasks= Vue.resource('/tasks{/id}')

export default {
    add: task => tasks.save({}, task),
    getAll: function(){
                        return tasks.get({})
                      },
    get: id => tasks.get({id}),
    update: task => tasks.update({id : task.id}, task),
    remove: id => tasks.remove({id})
}