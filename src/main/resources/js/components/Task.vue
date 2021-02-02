<template>
    <div>
        {{task.name}} <br/>
        От кого: <user :user="task.fromWhom"></user>
        <label for="bth">Текст:</label> <input class="btn" v-if="showText === false" name="btn" type="button" value="открыть" @click="showText = true"/>
                <input class="btn" v-else type="button" value="скрыть" @click="showText = false"/>
        <div v-show="showText">{{task.text}}</div> <br/>

            <input v-if="task.status === 'NEW'" @click="takeToWork()" type="button" value="Взять в работу">

            <input v-if="task.status === 'PERFORMED'" @click="complete()" type="button" value="Завершить" >
    </div>
</template>

<script>
    import {mapActions} from 'vuex'
    import User from './User.vue';

    export default {
        props: {
            task: Object
        },

        data() {
            return {
                showText: false
            }
        },

        components: {
            User
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