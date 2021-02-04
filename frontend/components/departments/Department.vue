<template>
    <div @click="click(department)">
        <p class="name" @click="select ? select = false : select = true">{{department.name}}</p>

        <div v-if="select===true">
            <p>Начальник: <i v-if="department.chief === null || department.chief === undefined">Нет информации</i>
                            <User v-else :user="department.chief"></User>
            </p>
            Работники:
            <ul>
                <li v-for="employee in department.employees">{{employee.name}} {{employee.surname}} - {{employee.position}}</li>
            </ul>
        </div>
    </div>
</template>

<script>
    import User from '../users/User.vue'

    export default {

        props: {
            department:{
                type: Object
            }
        },

        components:{
            User
        },

        data() {
            return{
                select: false
            }
        },

        methods:{
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