import gulp from 'gulp'
import gutil from 'gulp-util'
import livereload from 'gulp-livereload'
import args from './lib/args'

gulp.task('watch', (cb) => {
  // This task runs only if the
  // watch argument is present!
  if (!args.watch) return cb();

  // Start livereload server
  livereload.listen({
    quiet: !args.verbose,
  });

  gutil.log('Starting', gutil.colors.cyan('\'livereload-server\''));

  // The watching for javascript files is done by webpack
  // Check out ./tasks/scripts.js for further info.
  gulp.watch('app/styles/**/*.css', ['styles:css']);
  gulp.watch('app/styles/**/*.less', ['styles:less']);
  gulp.watch('app/pages/**/*.html', ['pages']);
  gulp.watch('app/images/**/*', ['images']);
});