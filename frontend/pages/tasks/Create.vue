<template>
    <div>
        <h2>Создать задачу</h2>

        <form action="/mytasks/create" method="post">
            <label for="name">Название:</label>
                <input name="name" type="text" v-model="task.name"><br/>
            <label for="toWhom">Кому:</label>
                <div name="toWhom">
                    <find-user class="select" v-if="task.toWhom == ''"></find-user></div>
                    <User v-if="task.toWhom !== ''" :user="task.toWhom"></User>
            <label for="text">Текст:</label><br/>
            <textarea name="text" v-model="task.text"></textarea><br/>
            <input type="submit" @click="addTask()">
        </form>
    </div>
</template>

<script>
    import FindUser from '../../components/users/findUser.vue'
    import User from "../../components/users/User.vue";
    import {mapActions, mapGetters} from 'vuex'

    export default {

        components: {
            FindUser,
            User
        },

        data() {
            return {
                task: {
                    name: '',
                    toWhom: '',
                    fromWhom: '',
                    text:'',
                    status: 'NEW'
                }
            }
        },

        computed: mapGetters(['getProfile']),

        created() {
            this.$root.$on('selectUser', (selectedUser) => {
                this.task.toWhom = selectedUser;})
        },

        methods: {
            ...mapActions(['addTaskAction']),

            addTask(){
                this.task.fromWhom = this.getProfile;
                delete this.task.fromWhom.password;
                delete this.task.fromWhom.username;
                delete this.task.toWhom.password;
                delete this.task.toWhom.username;
                this.addTaskAction(this.task);
            }
        }
    }
</script>

<style scoped>

    .select {
        cursor: pointer;
    }
</style>