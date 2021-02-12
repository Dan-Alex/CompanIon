<template>
    <div>
        <tasks-list
                :name="'Новые задачи'"
                :tasks="getNewTasks">
        </tasks-list>
    </div>

</template>

<script>
    import {mapGetters, mapActions} from 'vuex'
    import TasksList from "../../components/tasks/TasksList.vue";

    export default {

        components: {
            TasksList
        },

        computed: mapGetters(['getNewTasks']),

        methods: mapActions(['getNewTasksAction']),

        created() {
            this.getNewTasksAction();
        }
    }
</script>

<style scoped>

</style>