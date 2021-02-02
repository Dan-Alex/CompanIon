<template>
    <div>
        <h2>Создать задачу</h2>

        <form action="/mytasks/create" method="post">
            <label for="name">Название:</label>
                <input name="name" type="text" v-model="task.name"><br/>
            <label for="toWhom">Кому:</label>
                <div name="toWhom">
                    <find-user v-if="!isToWhomSelected"></find-user></div>
                    <User v-if="isToWhomSelected":user="task.toWhom"></User>
            <label for="text">Текст:</label><br/>
            <textarea name="text" v-model="task.text"></textarea><br/>
            <input type="submit" @click="addTask()">
        </form>
    </div>
</template>

<script>
    import FindUser from '../../components/findUser.vue'
    import User from "../../components/User.vue";
    import {mapActions, mapGetters} from 'vuex'

    export default {
        data() {
            return {
                task: {
                    name: '',
                    toWhom: '',
                    fromWhom: '',
                    text:'',
                    status: 'NEW'
                },
                isToWhomSelected: false
            }
        },

        components: {
            FindUser,
            User
        },

        computed: mapGetters(['getProfile']),

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
        },

        created() {
            this.$root.$on('selectUser', (selectedUser) => {
                this.task.toWhom = selectedUser;
                if (this.task.toWhom !== '')
                    this.isToWhomSelected = true
            })
        }
    }
</script>

<style scoped>

</style>