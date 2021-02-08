<template>
    <div>
        <h2>Создать задачу</h2>
    <form action="/my_tasks/create">
            <label for="name">Название:</label>
                <input name="name" type="text" v-model="task.name"><br/>
            <label for="toWhom">Кому:</label>
                <div name="toWhom">
                    <find-user class="select" v-if="task.toWhom === ''"></find-user></div>
                    <User v-if="task.toWhom !== ''" :user="task.toWhom"></User>
            <label for="text">Текст:</label><br/>
            <textarea name="text" v-model="task.text"></textarea><br/>
            <label for="files">Прикрепить файлы:</label>
                <div name="files">
                    <input type="button" value="Добавить файл" @click="task.files.push('')">
                    <ul>
                        <li v-for="file in task.files">
                            <input type="file" class="file">
                        </li>
                    </ul>
                </div>

            <input type="button" value="Отправить" @click="addTask()">
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
                    status: 'NEW',
                    files: []
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

               for (let i = 0; i < this.task.files.length; i++) {
                    this.task.files[i] = document.getElementsByClassName('file')[i].files[0];
                }

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