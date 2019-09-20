import gulp from "gulp";
import gulpSequence from "gulp-sequence";

gulp.task('build', gulpSequence(
  'clean', [
    'scripts',
    'styles',
    'pages',
    'images',
    'watch'
  ]
));
