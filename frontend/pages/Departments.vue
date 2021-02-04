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
    import {mapGetters} from 'vuex'
    import Department from '../components/departments/Department.vue'
    import FindDepartment from "../components/departments/findDepartment.vue";

    export default {

        components: {
            Department,
            FindDepartment
        },

        computed: mapGetters(['getAllDepartments'])
    }
</script>

<style scoped>

</style>