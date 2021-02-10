<template>
    <div>
        <tasks-list
                :name="'Завершенные'"
                :tasks="getCompletedTasks">
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

        computed: mapGetters(['getCompletedTasks']),

        methods: mapActions(['getCompletedTasksAction']),

        created(){
            this.getCompletedTasksAction();
        }
    }
</script>

<style scoped>

</style>