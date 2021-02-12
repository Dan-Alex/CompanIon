<template>
    <div>
        <br/>
           <div v-if="getRoles.includes('ROLE_ADMIN')">
               <input type="button" value = "Добавить отдел" @click="showForm = true"><br/>
                <department-form v-if="showForm"></department-form><br/>
           </div>
        Поиск: <find-department></find-department>

        <h3>Список всех отделов:</h3>
        <ul>
            <li v-for="department in getAllDepartments"><Department :department="department"></Department></li>
        </ul>
    </div>
</template>

<script>
    import {mapGetters} from 'vuex'
    import Department from '../components/departments/Department.vue'
    import FindDepartment from "../components/departments/findDepartment.vue";
    import DepartmentForm from "../components/departments/DepartmentForm.vue"

    export default {

        data() {
            return {
                showForm: false
            }
        },

        components: {
            Department,
            FindDepartment,
            DepartmentForm
        },

        computed: mapGetters(['getAllDepartments', 'getRoles'])
    }
</script>

<style scoped>

</style>