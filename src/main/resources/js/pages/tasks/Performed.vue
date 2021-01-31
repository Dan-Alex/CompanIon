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
    import TasksList from "../../components/TasksList.vue";

    export default {

        computed: mapGetters(['getPerformedTasks']),

        components: {
            TasksList
        }
    }
</script>

<style scoped>

</style>