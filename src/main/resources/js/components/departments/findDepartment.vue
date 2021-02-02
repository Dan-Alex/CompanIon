<template>
    <div>
        <input type="text" name="find" v-model="findItem" v-on:keyup.enter="find()">
        <input type="button" @click="find()" value="Найти">
        <p v-if="notFound">Не найден</p>
        <ul>
            <li v-for="department in departments">
                <Department :department="department"></Department>
            </li>
        </ul>
    </div>
</template>

<script>
    import {mapGetters} from 'vuex'
    import Department from "./Department.vue";

    export default {

        data(){
            return {
                departments: [],
                findItem: '',
                notFound: false,
            }
        },

        components:{
            Department
        },

        computed: mapGetters(['getAllDepartments']),

        methods: {

            find() {
                this.departments = this.getAllDepartments.filter(i => i.name.toLowerCase().includes(this.findItem.toLowerCase()));
                this.ifNotFound()
            },

            ifNotFound() {
                this.notFound = this.departments.length === 0;
            }
        }
    }
</script>

<style scoped>

</style>