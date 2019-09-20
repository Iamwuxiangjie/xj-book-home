import gulp from "gulp";
import gulpif from "gulp-if";
import livereload from "gulp-livereload";
import args from "./lib/args";

gulp.task('pages', () => {
  return gulp.src('app/pages/*.html')
    .pipe(gulp.dest("src/main/resources/static"))
    .pipe(gulp.dest("target/classes/static"))
    .pipe(gulpif(args.watch, livereload({
      port: 7772
    })));
});
