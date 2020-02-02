<template>
  <button class="add-btn" @click="handleClick">+</button>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";

@Component
export default class AddTaskButton extends Vue {
  @Prop() private msg!: string;

  handleClick() {
    this.$emit("click");
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.add-btn {
  border: none;
  display: inline-block;
  padding: 0;
  vertical-align: middle;
  overflow: hidden;
  text-decoration: none;
  text-align: center;
  cursor: pointer;
  white-space: nowrap;
  outline: none;
  color: #fff;
  background-color: #4a4141;
  border-radius: 50%;
  font-size: 56px !important;
  height: 60px;
  width: 60px;
  position: fixed;
  bottom: 20px;
  right: 20px;
}
.add-btn:hover {
  background-color: black;
}
</style>
