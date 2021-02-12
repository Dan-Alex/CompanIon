<template>
    <div class="task">
        <p class="name" @click="select ? select = false : select = true">{{task.name}}</p>

        <div v-if="select===true">
            <p v-if="getProfile.id === task.toWhom.id">От кого: <user :user="task.fromWhom"></user></p>
            <p v-else>Кому: <user :user="task.toWhom"></user></p>

            <label for="text">Текст:</label>
                    <div name="text">{{task.text}}</div> <br/>

            <p v-if="task.status === 'COMPLETED'">Отчет:{{task.report}}</p>

            <label for="files">Прикрепленные файлы:</label>
                <ul name="files">
                    <li v-for="file in task.documents"><a :href="'/files/'+file.filename" :download="file.shortName">{{file.shortName}}</a></li>
                </ul>
            <br/>

            <input class="btn" v-if="task.status === 'NEW'" @click="takeToWork()" type="button" value="Взять в работу"><br/>

                <div v-if="task.status === 'PERFORMED'">
                    <label>Отчет:</label><br/>
                        <textarea v-model="task.report"></textarea><br/><br/>
                    <label for="files">Прикрепить файлы:</label>
                    <div name="files">
                        <input type="button" value="Добавить файл" @click="files.push('')">
                        <ul>
                            <li v-for="file in files">
                                <input type="file" class="file">
                            </li>
                        </ul>
                    </div>
                    <br/>
                    <input class="btn" @click="complete()" type="button" value="Завершить">
                </div>
    </div>

    </div>
</template>

<script>
    import {mapActions, mapGetters} from 'vuex'
    import User from '../users/User.vue';

    export default {

        props: {
            task: Object
        },

        components: {
            User
        },

        data() {
            return {
                files: [],
                select: false,
            }
        },

        computed: mapGetters(['getProfile']),

        methods:{
            ...mapActions(['updateTaskAction']),

            takeToWork(){
                this.task.status = 'PERFORMED';
                this.updateTaskAction({
                                        task: this.task,
                                        files: this.files
                });
            },

            complete(){
                this.task.status = 'COMPLETED';

                for (let i = 0; i < this.files.length; i++) {
                    this.files[i] = document.getElementsByClassName('file')[i].files[0];
                }

                this.updateTaskAction({
                                        task: this.task,
                                        files: this.files
                });
            }
        }

    }
</script>

<style scoped>
    .name {
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