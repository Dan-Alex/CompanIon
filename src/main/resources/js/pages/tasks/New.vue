<template>
    <div>
        <tasks-list
                :name="'Новые задачи'"
                :tasks="getNewTasks">
        </tasks-list>
    </div>

</template>

<script>
    import {mapGetters} from 'vuex'
    import TasksList from "../../components/TasksList.vue";

    export default {

        computed: mapGetters(['getNewTasks']),

        components: {
            TasksList
        }
    }
</script>

<style scoped>

</style>