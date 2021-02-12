<template>
    <div>
        <h2>Создать задачу</h2>
    <form action="/my_tasks">
            <label for="name">Название:</label>
                <input name="name" type="text" v-model="task.name"><br/><br/>
            <label for="toWhom">Кому:</label>
                <div name="toWhom">
                    <find-user class="select" v-if="task.toWhom === ''"></find-user></div>
                    <User v-if="task.toWhom !== ''" :user="task.toWhom"></User>
            <label for="text">Текст:</label><br/>
            <textarea name="text" v-model="task.text"></textarea><br/><br/>
            <label for="files">Прикрепить файлы:</label>
                <div name="files">
                    <input type="button" value="Добавить файл" @click="task.documents.push('')">
                    <ul>
                        <li v-for="document in task.documents">
                            <input type="file" class="file">
                        </li>
                    </ul>
                </div>

            <input class="btn" type="button" value="Отправить" @click="addTask()">
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
                    documents: []
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

                if (this.task.documents !== undefined) {
                    for (let i = 0; i < this.task.documents.length; i++) {
                        this.task.documents[i] = document.getElementsByClassName('file')[i].files[0];
                    }
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

    h2 {
        text-align: center;
    }

    .select {
        cursor: pointer;
    }

    textarea {
        width: 100%;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
        overflow: auto;
        resize: none;
        height: 300px;
    }

    .btn {
        padding: 1em;
        width: 100%;
        font-size: 16pt;
        background-color: coral;
        cursor: pointer;
    }
</style>