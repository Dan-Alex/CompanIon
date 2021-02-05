<template>
    <div>
        <p> {{getProfile.name}} {{getProfile.surname}} </p>
        <p> {{getProfile.position}}</p>
        <p> {{getProfile.departmentName}}</p>
        <p> {{getProfile.email}}</p>
        <p> {{getProfile.phone}}</p>
    </div>
</template>

<script>
    import {mapGetters} from 'vuex'

    export default {

        computed: mapGetters(['getProfile', 'getRoles']),
    }
</script>

<style scoped>

</style>