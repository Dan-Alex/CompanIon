<template>
    <div>
        <tasks-list
                :name="'В работе'"
                :tasks="getPerformedTasks">
        </tasks-list>
    </div>

</template>

<script>
    import {mapGetters} from 'vuex'
    import TasksList from "../../components/tasks/TasksList.vue";

    export default {

        components: {
            TasksList
        },

        computed: mapGetters(['getPerformedTasks']),
    }
</script>

<style scoped>

</style>