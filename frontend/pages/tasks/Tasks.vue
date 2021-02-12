<template>

    <div>

        <div class="tasks_menu">
            <router-link class="tasks_menu_item" :to="{name : 'new'}">Новые({{getNewTasks.length}})</router-link>
            <router-link class="tasks_menu_item" :to="{name : 'performed'}">В работе({{getPerformedTasks.length}})</router-link>
            <router-link class="tasks_menu_item" :to="{name : 'completed'}">Завершенные</router-link>
            <router-link class="tasks_menu_item" :to="{name : 'sent'}">Отчеты</router-link>
            <router-link class="tasks_menu_item" :to="{name:'create'}">Создать</router-link>
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

    .tasks_menu {
        display: grid;
        grid-template-columns: repeat(5, 1fr);
        justify-items: stretch;
    }

    .tasks_menu_item {
        text-align: center;
        padding: 1em;
    }
</style>