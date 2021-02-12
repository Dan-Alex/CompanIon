<template>
    <div>
        <tasks-list
                :name="'В работе'"
                :tasks="getPerformedTasks">
        </tasks-list>
    </div>

</template>

<script>
    import {mapActions, mapGetters} from 'vuex'
    import TasksList from "../../components/tasks/TasksList.vue";

    export default {

        components: {
            TasksList
        },

        computed: mapGetters(['getPerformedTasks']),

        methods: mapActions(['getPerformedTasksAction']),

        created() {
            this.getPerformedTasksAction();
        }
    }
</script>

<style scoped>

</style>