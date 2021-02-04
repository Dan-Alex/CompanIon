<template>
    <div>
        <h2>{{name}}</h2>
        <p v-show="tasks.length === 0 || tasks.length === undefined">Нет задач</p>
        <ul>
            <li v-for="task in tasks">
                <task :task="task"></task>
            </li>
        </ul>
    </div>
</template>

<script>
    import Task from "./Task.vue";

    export default {

        props: {
            name: String,
            tasks: Array
        },

        components: {
            Task
        }
    }
</script>

<style scoped>

</style>