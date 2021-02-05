import Vue from 'vue'

const users = Vue.resource('/users{/id}')
const usersFiles = Vue.resource('/users{/id}/files')

export default {
    add: user => users.save({}, user),
    getAll: function(){
                return users.get({})
            },
    get: id => users.get({id}),
    update: user => users.update({id : user.id}, user),
    delete: user => users.remove({id: user.id}),
    getFiles: user => usersFiles.get({id: user.id}),
    addFile: (file, user) => usersFiles.save({id: user.id}, file)
}