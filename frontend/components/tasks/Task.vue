<template>
    <div>
        {{task.name}} <br/>
        От кого: <user :user="task.fromWhom"></user>
        <label for="bth">Текст:</label> <input class="btn" v-if="showText === false" name="btn" type="button" value="открыть" @click="showText = true"/>
                <input class="btn" v-else type="button" value="скрыть" @click="showText = false"/>
        <div v-show="showText">{{task.text}}</div> <br/>
        <label for="files">Прикрепленные файлы:</label>
            <ul name="files">
                <li v-for="file in task.documents"><a :href="'/files/'+file.filename">{{file.shortName}}</a></li>
            </ul>
            <input v-if="task.status === 'NEW'" @click="takeToWork()" type="button" value="Взять в работу">

            <input v-if="task.status === 'PERFORMED'" @click="complete()" type="button" value="Завершить" >
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
                showText: false
            }
        },

        methods:{
            ...mapActions(['updateTaskAction']),

            takeToWork(){
                this.task.status = 'PERFORMED';
                this.updateTaskAction(this.task);
            },

            complete(){
                this.task.status = 'COMPLETED';
                this.updateTaskAction(this.task);
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