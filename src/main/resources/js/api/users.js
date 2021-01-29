import Vue from 'vue'

const users = Vue.resource('/users{/id}')

export default {
    add: user => users.save({}, user),
    getAll: function(){
                        return users.get({})
                      },
    get: id => users.get({id}),
    update: user => users.update({id : user.id}, user),
    delete: user => users.remove({id: user.id})
}