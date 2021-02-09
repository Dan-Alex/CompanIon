<template>
    <div>
        {{task.name}} <br/>
        От кого: <user :user="task.fromWhom"></user>
        <label for="bth">Текст:</label> <input class="btn" v-if="showText === false" name="btn" type="button" value="открыть" @click="showText = true"/>
                <input class="btn" v-else type="button" value="скрыть" @click="showText = false"/>
        <div v-show="showText">{{task.text}}</div> <br/>
        <p v-if="task.status === 'COMPLETED'">Отчет:{{task.report}}</p>
        <label for="files">Прикрепленные файлы:</label>
            <ul name="files">
                <li v-for="file in task.documents"><a :href="'/files/'+file.filename" :download="file.shortName">{{file.shortName}}</a></li>
            </ul>



        <input v-if="task.status === 'NEW'" @click="takeToWork()" type="button" value="Взять в работу">

            <div v-if="task.status === 'PERFORMED'">
                <label for="report">Отчет:</label>
                    <textarea name="report" v-model="task.report"></textarea><br/>
                <label for="files">Прикрепить файлы:</label>
                <div name="files">
                    <input type="button" value="Добавить файл" @click="files.push('')">
                    <ul>
                        <li v-for="file in files">
                            <input type="file" class="file">
                        </li>
                    </ul>
                </div>
                <input  @click="complete()" type="button" value="Завершить">
            </div>


    </div>
</template>

<script>
    import {mapActions} from 'vuex'
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
                showText: false
            }
        },

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
    .btn {
        background-color: white;
        border: none;
        outline: none;
        text-align: center;
        cursor: pointer;
        font-size: 18px;
    }
</style>