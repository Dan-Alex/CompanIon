import Vue from 'vue'

const departments= Vue.resource('/departments{/id}')

export default {
    add: department => departments.save({}, department),
    getAll: function(){
                        return departments.get({})
            },
    get: id => departments.get({id}),
    update: department => departments.update({id : department.id}, department),
    remove: department => departments.remove({id: department.id})
}