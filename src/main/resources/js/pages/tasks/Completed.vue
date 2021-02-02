<template>
    <div>
        <tasks-list
                :name="'Завершенные'"
                :tasks="getCompletedTasks">
        </tasks-list>
    </div>

</template>

<script>
    import {mapGetters} from 'vuex'
    import TasksList from "../../components/tasks/TasksList.vue";

    export default {

        computed: mapGetters(['getCompletedTasks']),

        components: {
            TasksList
        }
    }
</script>

<style scoped>

</style>