<template>

    <div>

        <div>
            <router-link :to="{name : 'new'}">Новые({{getNewTasks.length}})</router-link>
            <router-link :to="{name : 'performed'}">В работе({{getPerformedTasks.length}})</router-link>
            <router-link :to="{name : 'completed'}">Завершенные</router-link>
            <router-link :to="{name : 'sent'}">Отчеты</router-link>
            <router-link :to="{name:'create'}">Создать</router-link>
        </div>

        <router-view></router-view>
    </div>
</template>

<script>
    import {mapGetters, mapActions} from 'vuex'
    import TasksList from "../../components/tasks/TasksList.vue";

    export default {

        components: {
            TasksList
        },

        computed: mapGetters(['getNewTasks', 'getPerformedTasks']),

        methods: mapActions(['getNewTasksAction', 'getPerformedTasksAction']),

        created() {
            this.getNewTasksAction();
            this.getPerformedTasksAction();
        }
    }
</script>

<style scoped>

</style>