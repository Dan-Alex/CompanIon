<template>
    <div v-if="!isUpdate" @click="click(department)">

        <p class="name" @click="select ? select = false : select = true">{{department.name}}</p>

        <div v-if="select===true">
            <p>Начальник: <i v-if="department.chief === ''">Нет информации</i>
                            <User v-else :user="department.chief"></User>
            </p>
            Работники:
            <ul>
                <li v-for="employee in department.employees">{{employee.name}} {{employee.surname}} - {{employee.position}}</li>
            </ul>
            <div v-if="getRoles.includes('ROLE_ADMIN')">
                <input type="button" value="Изменить" @click="isUpdate = true">
                <input type="button" value="Удалить" @click="deleteDepartmentAction(department)">
            </div>
        </div>
    </div>

    <department-form v-else :departmentAttr="department"></department-form>
</template>

<script>
    import {mapActions, mapGetters} from 'vuex'
    import User from '../users/User.vue'
    import DepartmentForm from './DepartmentForm.vue'

    export default {

        props: {
            department:{
                type: Object
            }
        },

        components:{
            User,
            DepartmentForm
        },

        data() {
            return{
                select: false,
                isUpdate: false
            }
        },

        computed: {
            ...mapGetters(['getRoles'])
        },

        created(){
            if (this.department.chief === null || this.department.chief === undefined)
                this.department.chief = ''
        },

        methods:{
            ...mapActions(['deleteDepartmentAction']),

            click(department){
                this.$root.$emit('selectDepartment', department)
            }
        }
    }
</script>

<style scoped>
    .name {
        cursor: pointer;
    }
</style>