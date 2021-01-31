<template>

    <div>

        <div>
            <router-link :to="{name : 'new'}">Новые({{getNewTasks.length}})</router-link>
            <router-link :to="{name : 'performed'}">В работе({{getPerformedTasks.length}})</router-link>
            <router-link :to="{name : 'completed'}">Завершенные({{getCompletedTasks.length}})</router-link>
            <router-link :to="{name : 'sent'}">Отправленные</router-link>
            <router-link to="create">Создать</router-link>
        </div>

        <router-view></router-view>
    </div>
</template>

<script>
    import {mapGetters, mapActions} from 'vuex'
    import TasksList from "../components/TasksList.vue";

    export default {

        components: {
            TasksList
        },

        computed: mapGetters(['getTasks','getNewTasks', 'getPerformedTasks', 'getCompletedTasks']),

        methods: mapActions(['getAllTasksAction']),

        created() {
            this.getAllTasksAction()
        }
    }
</script>

<style scoped>

</style>