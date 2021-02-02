<template>
    <div>
        <input type="text" name="find" v-model="findItem" v-on:keyup.enter="find()">
        <select v-model="findBy">
            <option v-for="option in options" v-bind:value="option.value">
                {{ option.text }}
            </option>
        </select>
        <input type="button" @click="find()" value="Найти">
        <p v-if="notFound">Не найден</p>
        <ul>
            <li v-for="user in users">
                <User :user="user"></User>
            </li>
        </ul>
    </div>
</template>

<script>
    import {mapGetters} from 'vuex'
    import User from "./User.vue";

    export default {

        data(){
            return {
                users: [],
                findItem: '',
                notFound: false,
                findBy: 'byNameAndSurname',
                options: [
                    { text: 'по имени и фамилии', value: 'byNameAndSurname' },
                    { text: 'по должности ', value: 'byPosition' },
                    { text: 'по отделу', value: 'byDepartment'}
                ]
            }
        },

        components:{
           User
        },

        computed: mapGetters(['getUsers']),

        methods: {

            find(){
                switch(this.findBy) {
                    case 'byNameAndSurname':
                        this.findByNameAndSurname(this.findItem);
                        break;
                    case 'byPosition':
                        this.findByPosition(this.findItem);
                        break;
                    case 'byDepartment':
                        this.findByDepartment(this.findItem);
                        break;
                }
            },

            findByNameAndSurname(nameSurname) {
                const splits = nameSurname.split(' ', 2);
                this.users = this.getUsers;
                splits.forEach(s =>
                    this.users = this.users.filter(i => i.name.toLowerCase() === s.toLowerCase() ||
                                                        i.surname.toLowerCase() === s.toLowerCase()));
                this.ifNotFound()
            },

            findByPosition(position) {
                this.users = this.getUsers.filter(i => i.position.toLowerCase().includes(position.toLowerCase()));
                this.ifNotFound()
            },

            findByDepartment(departmentName) {
                this.users = this.getUsers.filter(i => i.departmentName.toLowerCase().includes(departmentName.toLowerCase()));
                this.ifNotFound()
            },

            ifNotFound() {
                this.notFound = this.users.length === 0;
            }
        }
    }
</script>

<style scoped>

</style>