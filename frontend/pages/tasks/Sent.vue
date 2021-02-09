<template>
    <div>
        <tasks-list
                :name="'Отчеты'"
                :tasks="getSentTasks">
        </tasks-list>
    </div>

</template>

<script>
    import {mapActions, mapGetters} from 'vuex';
    import TasksList from "../../components/tasks/TasksList.vue";


    export default {

        computed: mapGetters(['getSentTasks']),

        methods: mapActions(['getSentTasksAction']),

        components: {
            TasksList
        },

        created() {
            this.getSentTasksAction()
        }

    }
</script>

<style scoped>

</style>