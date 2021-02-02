<template>
    <div>
        <tasks-list
                :name="'Новые задачи'"
                :tasks="tasks">
        </tasks-list>
    </div>

</template>

<script>
    import TasksList from "../../components/tasks/TasksList.vue";

    export default {
        props: {
            tasks: Array
        },

        components: {
            TasksList
        }
    }
</script>

<style scoped>

</style>