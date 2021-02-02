<template>
    <div>
        <br/>
        Поиск: <find-department></find-department>

        <h3>Список всех отделов:</h3>
        <ul>
            <li v-for="department in getAllDepartments"><Department :department="department"></Department></li>
        </ul>
    </div>
</template>

<script>
    import {mapGetters, mapActions} from 'vuex'
    import Department from '../components/departments/Department.vue'
    import FindDepartment from "../components/departments/findDepartment.vue";

    export default {
        computed: mapGetters(['getAllDepartments']),

        methods: mapActions(['getAllDepartmentsAction']),

        components: {
            Department,
            FindDepartment
        },

        created(){
            this.getAllDepartmentsAction()
        }
    }
</script>

<style scoped>

</style>