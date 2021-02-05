import Vue from 'vue'


const files = Vue.resource('/files{/filename}')

export default {
    add: file => files.save(file),
}