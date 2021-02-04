import Vue from 'vue'

const companyAPI = Vue.resource('/company{/id}')

export default {
    get: () => companyAPI.get(),
    update: company => companyAPI.update({id: company.id}, company)
}