<template>
  <div class="card-container" @dragstart="handleDrag($event)" @dragend="handleDragEnd($event)">
    <div class="completed" v-if="task.completionDate !=null">Completed on :{{task.completionDate}}</div>
    <h2>{{task.title}}</h2>
    <div class="content">{{task.description}}</div>
    <div class="due-date">Due Date: {{task.dueDate}}</div>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";

@Component
export default class KanbanCard extends Vue {
  @Prop() private msg!: string;
  @Prop() private task!: object;

  handleDrag(event: object) {
    this.$emit("dragstart", event);
  }

  handleDragEnd(event: object) {
    this.$emit("dragend", event);
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.card-container {
  text-align: left;
  width: 300px;
  border: 2px solid;
  border-color: #efefef;
  border-radius: 5px;
  margin-bottom: 20px;
  -webkit-box-shadow: 0 6px 6px -6px #888;
  -moz-box-shadow: 0 6px 6px -6px #888;
  box-shadow: 0 6px 6px -6px #888;
  padding: 20px;
}
h2 {
  color: #6b6b6b;
  margin: 0 0 10px 0;
  font-weight: 500;
}
.completed {
  background-color: #76d618;
  padding: 2px;
  float: right;
  margin-top: -20px;
  margin-right: -20px;
  border-radius: 2px;
  font-size: 11px;
  font-weight: 600;
  color: #efefef;
}
.content {
  color: #818182;
  margin-bottom: 25px;
  font-weight: 500;
  font-size: 15px;
}
.due-date {
  color: #6d6d6d;
  font-weight: 800;
  font-size: 11px;
}
</style>
